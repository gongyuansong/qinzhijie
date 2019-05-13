//logs.js
const util = require('../../utils/util.js')

Page({
  data: {
    bookList: [],
    lyricList: []
  },
  onLoad: function() {
    this.setData({
      logs: (wx.getStorageSync('logs') || []).map(log => {
        return util.formatTime(new Date(log))
      })
    })
  },

  //搜索框文本内容显示
  inputBind: function(event) {
    this.setData({
      inputValue: event.detail.value
    })
    console.log('bindInput' + this.data.inputValue)

  },
  query: function(event) {
    console.log(this.data.inputValue)
    var that = this
    wx.request({
      url: 'https://127.0.0.1:8081/station/search',
      method: 'POST',
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      data: {
        queryStr: this.data.inputValue
      },
      success: function(res) {
        console.log(res)
        var searchData = res.data
        if (res.data.data !== null) {
          that.setData({
            bookList: res.data.data.bookList,
            lyricList: res.data.data.lyricList
          })
        }

        var pages = getCurrentPages()
        var curPages = pages[pages.length - 1].route
        console.log(curPages)

        if ('pages/search/search' !== curPages) {
          wx.navigateTo({
            url: '../search/search'
          })
        }
      }
    })  
  },
  showBookDetail(e) {
    var id = e.currentTarget.dataset.id
    wx.navigateTo({
      url: '../book/book?bookId=' + id
    })
  },
  showLyricDetail(e) {
    var id = e.currentTarget.dataset.id
    wx.navigateTo({
      url: '../lyric/lyric?lyricId=' + id
    })
  }
})