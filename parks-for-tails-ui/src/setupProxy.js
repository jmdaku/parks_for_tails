const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function (app) {
  app.use(
    '/api',  // Specify the API endpoint to proxy
    createProxyMiddleware({
      target: 'http://localhost:8080', 
      changeOrigin: true,
    })
  );
};
