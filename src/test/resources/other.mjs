const LoggerFactory = Java.type('org.slf4j.LoggerFactory')
const logger = LoggerFactory.getLogger('es4x.tests.other')

export const sayHello = () => logger.info('Hello from Other!')