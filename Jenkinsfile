node{

    stage('SCM Checkout'){
        git credentialsId: 'dockerhub', url:  'https://github.com/Steven8519/sampe_app.git',branch: 'master'
    }

    stage(" Maven Clean Package"){
      def mavenHome =  tool name: "Maven-3.6.1", type: "maven"
      def mavenCMD = "${mavenHome}/bin/mvn"
      sh "${mavenCMD} clean package"

    }


    stage('Build Docker Image'){
        sh 'docker build -t steven8519/spring-boot-mongo .'
    }

    stage('Push Docker Image'){
        withCredentials([string(credentialsId: 'dockerhub1', variable: 'dockerhub1')]) {
          sh "docker login -u steven8519 -p ${dockerhub1}"
        }
        sh 'docker push steven8519/spring-boot-mongo'
     }

     stage("Deploy To Kuberates Cluster"){
       kubernetesDeploy(
         configs: 'springBootMongo.yml',
         kubeconfigId: 'kubernetes_server',
         enableConfigSubstitution: true
        )
     }

	  /**
      stage("Deploy To Kuberates Cluster"){
        sh 'kubectl apply -f pringBootMongo.yml'
      } **/

}

