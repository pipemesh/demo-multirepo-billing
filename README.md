# demo-multirepo-billing

The billing service. It calls the orders service through its client
library, which it never builds itself: the pipeline in
[demo-multirepo-pipeline](https://github.com/pipemesh/demo-multirepo-pipeline)
builds `orders-client.jar` from
[demo-multirepo-orders](https://github.com/pipemesh/demo-multirepo-orders)
and hands it to billing's build as a consumed entry.

This repository has no `pipemesh.yaml`: it is added to the org, and the
pipeline repository declares it.
