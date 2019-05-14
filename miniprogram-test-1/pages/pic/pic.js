//logs.js
const util = require('../../utils/util.js')

Page({
  data: {
    url: ''
  },
  onLoad: function(event) {
    var that = this
    that.setData({
      url: event.url
    })
  }
})