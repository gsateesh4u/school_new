node {
  stage("Clone project") {
    git branch: 'main', url: 'https://github.com/gsateesh4u/school_new.git'
  }

  stage("Build project with test execution") {
    sh "./mvnw clean install"
  }

  jacoco(
    execPattern: '**/*.exec',
    sourcePattern: 'src/main/java',
    exclusionPattern: 'src/test*'
  )
}