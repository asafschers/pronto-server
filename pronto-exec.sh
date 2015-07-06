#!/usr/bin/env bash
pushd /Users/asafschers1/Development/riskified/shopify_app
source ~/.rvm/scripts/rvm
type rvm | head -n 1
GITHUB_ACCESS_TOKEN=2ec317dff980ebb5b1f619b330455b2845c5e089 PULL_REQUEST_ID=958 pronto run -f github_pr -c origin/master
popd