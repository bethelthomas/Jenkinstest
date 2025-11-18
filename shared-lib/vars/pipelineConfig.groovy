def call() {
  def steps = new org.demo.steps()
  steps.say("Loading pipeline defaults")

  if (!env.VERSION) {
    env.VERSION = "0.1.0"
  }

  if (!env.IMAGE_NAME) {
    env.IMAGE_NAME = "fast-jenkins-demo:${env.VERSION}"
  }

  steps.say("VERSION=${env.VERSION}, IMAGE_NAME=${env.IMAGE_NAME}")
}
