# Log Monitoring using Elastic Stack

* This repository contains source code for Log Monitoring  with Elastic Stack on Docker Swarm (Single node).
* It generates kibana dashboard accessible at http://kibana:5601
* You can read more about Elastic Stack from [here](https://www.elastic.co/) and
* Docker Swarm from [here](https://docs.docker.com/engine/swarm/). 

####Workflow:

node's docker container logs  -- filebeat -->  elasticsearch  -->  kibana


#### Run the template
  - start the elastic stack
  ```
  ./start_elk.sh
  ```
  - stop the stack
  ```
  ./stop_elk.sh
  ```

