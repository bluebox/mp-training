pipeline {

    agent any

    options {
        timeout(time: 10, unit: 'MINUTES')
    }

    stages {

        stage('Pytest') {
            when {
                not {
                    branch "main"
                }
            }
            steps {
                echo 'checking pytest stage'
                sh 'pytest -v'
            }
        }

        stage('Pylint') {
            when {
                not {
                    branch "main"
                }
            }
            steps {
                echo 'checking pylint stage'
                sh 'pylint * --ignore=Jenkinsfile,__pycache__'
            }
        }

    }
}