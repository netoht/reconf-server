#!/bin/bash
curl -X PUT -v -H "Content-Type: text/plain" -d "'global value'" http://localhost:8080/crud/product/product1/component/component1/property/simple-property1?description=simple%20description