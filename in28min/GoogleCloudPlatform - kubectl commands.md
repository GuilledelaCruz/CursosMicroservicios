kubectl version
kubectl create deployment {deployment-name} --image={docker uri}
kubectl expose deployment {deployment-name} --type=LoadBalancer --port={port}
kubectl scale deployment {deployment-name} --replicas={n instances}
kubectl set image deployment {deployment-name} {container}={image}:{tag}

kubectl get events
kubectl get pods
kubectl get replicaset
kubectl get deployment
kubectl get service
kubectl get componentstatuses

kubectl explain pods
kubectl describe pod {id_pod}
kubectl delete pods {id_pod}

more commands:
[in28minutes GCP kubectl commands](https://github.com/in28minutes/spring-microservices-v2/tree/main/05.kubernetes#commands)
