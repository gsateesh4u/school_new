/* node {
  stage("Clone project") {
    git branch: 'main', url: 'https://github.com/gsateesh4u/school_new.git'
  }

  stage("Build project with test execution") {
    sh "./mvnw clean install"
  }

  jacoco(
    execPattern: '** /* *//*.exec',
    sourcePattern: 'src/main/java',
    exclusionPattern: 'src/test*'
  )
} */

node {
    def WORKSPACE = "/var/lib/jenkins/workspace/school-app"
    def dockerImageTag = "school-app${env.BUILD_NUMBER}"
try{
    notifyBuild('STARTED')
    stage('Clone Repo') {
        // for display purposes
        // Get some code from a GitHub repository
        git url: 'https://github.com/gsateesh4u/school_new.git',
            credentialsId: 'school-user',
            branch: 'main'
     }
     
    stage('Build docker') {
         dockerImage = docker.build("school-app:${env.BUILD_NUMBER}")
    }
    stage('Deploy docker'){
          echo "Docker Image Tag Name: ${dockerImageTag}"
          sh "docker stop school-app || true && docker rm school-app || true"
          sh "docker run --name school-app -d -p 8080:8080 school-app:${env.BUILD_NUMBER}"
    }
}catch(e){
    currentBuild.result = "FAILED"
    throw e
}finally{
    notifyBuild(currentBuild.result)
 }
}


def notifyBuild(String buildStatus = 'STARTED'){

  // build status of null means successful
  buildStatus =  buildStatus ?: 'SUCCESSFUL'

  // Default values
  def colorName = 'RED'
  def colorCode = '#FF0000'
  def now = new Date()

  // message
  def subject = "${buildStatus}, Job: ${env.JOB_NAME} FRONTEND - Deployment Sequence: [${env.BUILD_NUMBER}] "
  def summary = "${subject} - Check On: (${env.BUILD_URL}) - Time: ${now}"
  def subject_email = "School App Deployment"
  def details = """<p>${buildStatus} JOB </p>
    <p>Job: ${env.JOB_NAME} - Deployment Sequence: [${env.BUILD_NUMBER}] - Time: ${now}</p>
    <p>Check console output at "<a href="${env.BUILD_URL}">${env.JOB_NAME}</a>"</p>"""

  // Email notification
  emailext (
     to: "gsateesh4u@gmail.com",
     subject: subject_email,
     body: details,
     recipientProviders: [[$class: 'DevelopersRecipientProvider']]
  )

}