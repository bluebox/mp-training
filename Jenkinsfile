pipeline {

    agent any

    options {
        timeout(time: 10, unit: 'MINUTES')
    }

    stages {

        stage('Build') {
            steps {
            	echo "Creating Virtual Environment and Installing Requirements ... "
		        script {
			        if (!fileExists('/var/lib/jenkins/venv/')) {
		                sh 'mkdir /var/lib/jenkins/venv'
		            }
		        }
                sh 'python3.6 -m venv /var/lib/jenkins/venv/venv-training'
                sh '/var/lib/jenkins/venv/venv-training/bin/pip3 install --upgrade pip'
                sh '/var/lib/jenkins/venv/venv-training/bin/pip3 install wheel'
                sh '/var/lib/jenkins/venv/venv-training/bin/pip3 install pytest'
                sh '/var/lib/jenkins/venv/venv-training/bin/pip3 install pylint'

                script {
			        if (fileExists('requirements.txt')) {
		                sh '/var/lib/jenkins/venv/venv-training/bin/pip3 install -r requirements.txt'
		            }
		        }
            }
        }

        stage('Pytest') {
            when {
                not {
                    branch "main"
                }
            }
            steps {
                echo 'checking pytest stage'
                sh '/var/lib/jenkins/venv/venv-training/bin/pytest ./tasks -v'
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
                sh '/var/lib/jenkins/venv/venv-training/bin/pylint --fail-under=7 ./tasks'
            }
        }

    }
}