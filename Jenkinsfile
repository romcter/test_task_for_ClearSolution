pipeline {
    environment {
        registry = "romcter/test_task"
        registryCredential = 'dockerhub_id'
        dockerImage = ''
    }
    agent any
    stages {
        stage('Cloning our Git') {
            steps {
                git 'https://github.com/romcter/test_task_for_ClearSolution.git'
            }
        }
        stage('Building our image') {
            steps{
                script {
                    dockerImage = docker.build registry + ":$BUILD_NUMBER"
                }
            }
        }
        stage('Deploy our image') {
            steps{
                script {
                    docker.withRegistry( '', registryCredential ) {
                    dockerImage.push()
                }
            }
        }
    }
    stage('Cleaning up') {
        steps{
            sh "docker rmi $registry:$BUILD_NUMBER"
        }
    }
}
}


// pipeline {
//     agent any
//     stages {
//         stage('build') {
//             steps {
// //                 sh 'mvn -B -DskipTests clean package'
//                 echo 'Building process'
//             }
//         }
//         stage('test') {
//             steps {
//                 echo 'Testing process'
//             }
//         }
//         stage('deploy') {
//             steps {
//                 echo 'Deploying process'
//             }
//         }
//     }
// }