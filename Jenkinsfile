pipeline{
    agent any
    stages{
        stage('Jenkis Monitoria'){
            steps{
                echo 'Jankins monitoria interface'
            }
        }
        stage('Build Interface'){
            steps{
                build job: 'classroom.monitoria', wait: true
            }
        }

        stage('Build'){
            steps{
                sh 'mvn clean package'
            }
        }

        stage('Build Image'){

            steps{
                script{
                    monitoria = docker.build("c0d8/monitoria:${env.BUILD_ID}", "-f Dockerfile .")
                }
            }

        }

        stage('Push Image'){
            steps{
                script{
                    docker.withRegistry('https://registry.hub.docker.com', 'dockerhub-credential'){
                        monitoria.push("${env.BUILD_ID}")
                        monitoria.push("latest")
                    }
                }
            }
        }
       
    }
}