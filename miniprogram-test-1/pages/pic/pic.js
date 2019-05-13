//logs.js
const util = require('../../utils/util.js')

Page({
  data: {
    url: ''
  },
  onLoad: function(event) {
    var that = this
    console.log(1111111 + event.url)
    that.setData({
      url: event.url
    })
  }
})