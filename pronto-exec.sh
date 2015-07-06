#!/usr/bin/env bash
pushd /Users/asafschers1/Development/riskified/shopify_app
source ~/.rvm/scripts/rvm
type rvm | head -n 1
# set GITHUB_ACCESS_TOKEN
PULL_REQUEST_ID=958 pronto run -f github_pr -c origin/master
popd