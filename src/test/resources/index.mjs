import * as Other from './other'

const LoggerFactory = Java.type('org.slf4j.LoggerFactory')
const logger = LoggerFactory.getLogger('es4x.tests.index')

process.on('deploy', (deploy) => {
  const verticleConfig = JSON.parse(config.encode())
  logger.info(`Hello from ES4X, the catchphrase is: ${verticleConfig.catchphrase}`)
  Other.sayHello()
  deploy.complete()
})