import { boot } from 'quasar/wrappers'
import createApi from '../api/api'
export default boot(({ app }) => {
  app.config.globalProperties.$api = createApi()
})
