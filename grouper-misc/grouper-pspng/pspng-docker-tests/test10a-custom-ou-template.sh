#!/bin/bash
#
# description: test10a - custom ou template
# configs: posix-groups-bushy
#
# This test takes verifies that custom OU templates work
#

set -o errexit
set -o pipefail
set -o nounset

ME=$(basename "$0")

# Functions for test harness: read_test_config_files, test_start, start_docker, etc
# (This also pulls in functions.bash (log_always, temp-file functions, etc)
. "$(dirname "${BASH_SOURCE[0]}")/functions-pspng-testing.bash"


# This switches ldap properties to use morphString
#
tweak_grouper_config() {
  local DIR="${1:?USAGE: tweak_grouper_config <directory>}"

  log_always "Adding a custom ou-creation template to config"
  echo 'changeLog.consumer.pspng1.ouCreationLdifTemplate = dn: ${dn}||ou: ${ou}||objectclass: organizationalunit||description: test description' \
    >> $DIR/grouper-loader.properties
}


###
# Pull-in shell functions for generating config and verifying provisioning
# These are usually specific for different provisioning endpoints
# And the same 
#   Config Building: output_log4j_properties, output_grouper_loader_properties
#   Hooks: test_is_starting, test_is_ending
#   Provisioning verification: validate_provisioning <group> <correct members (comma-separated, alphabetical)>
# Defines: $flavor
read_test_config_files "${1:-posix-groups-bushy}"


# Note that the test is starting, saves start-time, etc
test_start "$ME" "Pspng ($flavor): provisioning OUs with a custom template"

################
## CONFIGURE GROUPER
## This will populate and define GROUPER_CONFIG_DIR

create_grouper_daemon_config

################
## START DOCKER

start_docker "${ME}_$flavor"


wait_for_grouper_daemon_to_be_running

create_test_folder

mark_test_folder_for_provisioning

create_group1_and_group2

add_members_to_group1 banderson agasper bbrown705
await_changelog_catchup

validate_provisioning "$GROUP1_NAME" "agasper,banderson,bbrown705"

add_group1_to_group2
await_changelog_catchup

validate_provisioning "$GROUP2_NAME" "agasper,banderson,bbrown705"

test_step "Checking that OU was created with a description"
description=$(run_in_grouper_daemon bash -c "myldapsearch -b dc=example,dc=edu ou=parentFolder | grep_ -i description | noAttributeLabels")

assert_equals "test description" "${description}" "Description of testFolder OU"

#make sure extra groups were not provisioned
validate_deprovisioning "$UNPROVISIONED_GROUP_NAME"
wrap_up
assert_empty "$ERRORS" "Check for exceptions in grouper_error.log"
test_success
