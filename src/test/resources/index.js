const LoggerFactory = Java.type('org.slf4j.LoggerFactory')
const logger = LoggerFactory.getLogger('es4x.tests.index')

process.on('deploy', (deploy) => {
  logger.info(`Hello from ES4X, the catchphrase is: ${config.catchphrase}`)
  require('other');
  deploy.complete()
})