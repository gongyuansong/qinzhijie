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