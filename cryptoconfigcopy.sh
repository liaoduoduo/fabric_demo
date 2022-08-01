#!/bin/bash

rm -rf ./src/main/resources/crypto-config

# shellcheck disable=SC2164
cd ./src/main/resources

mkdir crypto-config

# shellcheck disable=SC2164
cd ./crypto-config
cp -r /usr/local/dev/go/src/github.com/hyperledger/fabric-hbut/scripts/fabric-samples/test-network/organizations/ordererOrganizations/ ./
cp -r /usr/local/dev/go/src/github.com/hyperledger/fabric-hbut/scripts/fabric-samples/test-network/organizations/peerOrganizations/ ./

