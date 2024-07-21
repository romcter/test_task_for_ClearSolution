pipeline {
    agent any
    stages {
        stage('build') {
            steps {
//                 sh 'mvn -B -DskipTests clean package'
                echo 'Building process'
            }
        }
        stage('test') {
            steps {
                echo 'Testing process'
            }
        }
        stage('deploy') {
            steps {
                echo 'Deploying process'
            }
        }
    }
}