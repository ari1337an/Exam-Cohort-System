// @ts-nocheck

// Express, CORS
const express   = require('express')
const cors      = require('cors')

// Express Instance
const app       = express()

// Sequelize 
const {sequelize} = require('./models')

// Utils
const config      = require('./utils/config')
const middleware  = require('./utils/middleware')
const logger      = require('./utils/logger')

// Routes
const mainRoutes = require('./routes/mainRoutes')
const authRoutes = require('./routes/authRoutes')
const profileRoutes = require('./routes/profileRoutes')
const cohortRoutes = require('./routes/cohortRoutes')
const reevaluateRoutes = require('./routes/reevaluateRoutes')
const assignedCohortRoutes = require('./routes/assignedCohortRoutes')
const audioFileRoutes = require('./routes/audioFileRoutes')
const examRoutes = require('./routes/examRoutes')
const spreadsheetRoutes = require('./routes/spreadsheetRoutes')

/**
 * Database Connection
 * 
 * Using ORM: Sequelize
 */
const connectDB = async () => {
  try {
    logger.info('Connecting to Database...')
    await sequelize.authenticate()
    // await sequelize.sync({force:true})
    logger.success('Connected to Database!')
  } catch (error) {
    logger.error('Failed to Connect to Database!', error.message);
  }
};
connectDB()

/**
 * Use all core packages
 * 
 * CORS, Express, ExpressJSON
 */
app.use(cors())
app.use(express.static('build'))
app.use(express.json())

// Start Logging Requests in the console
// app.use(middleware.requestLogger)

/**
 * Use the Routes
 */
app.use('/', mainRoutes)
app.use('/api/auth/', authRoutes)
app.use('/api/profile/', profileRoutes)
app.use('/api/examcohort/', cohortRoutes)
app.use('/api/examcohort/', reevaluateRoutes)
app.use('/api/assignedcohort/', assignedCohortRoutes)
app.use('/api/audio/', audioFileRoutes)
app.use('/api/exam/', examRoutes)
app.use('/api/spreadsheet/', spreadsheetRoutes)

/**
 * Handle the unknown endpoint, if no controller is defined for requested endpoint
 * Which is a 404 Error Code
 */
app.use(middleware.unknownEndpoint)

module.exports = app