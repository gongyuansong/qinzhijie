
Page({
  data: {
    bookList: [],
    lyricList: [],
    queryStr: ''
  },
  onLoad: function (event) {
    var that = this
    wx.request({
      url: 'https://www.gysp.top:8081/station/search',
      method: 'POST',
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      data: {
        queryStr: event.queryStr
      },
      success: function (res) {
        if (res.data.data !== null) {
          that.setData({
            bookList: res.data.data.bookList,
            lyricList: res.data.data.lyricList
          })
        }
      }
    }) 
  },

  //搜索框文本内容显示
  inputBind: function(event) {
    this.setData({
      inputValue: event.detail.value
    })
  },
  query: function(event) {
    var that = this
    wx.request({
      url: 'https://www.gysp.top:8081/station/search',
      method: 'POST',
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      data: {
        queryStr: this.data.inputValue
      },
      success: function(res) {
        var searchData = res.data
        if (res.data.data !== null) {
          that.setData({
            bookList: res.data.data.bookList,
            lyricList: res.data.data.lyricList
          })
        }
      }
    })  
  },
  showBookDetail(e) {
    var id = e.currentTarget.dataset.id
    wx.navigateTo({
      url: '../detailList/detailList?bookId=' + id
    })
  },
  showLyricDetail(e) {
    var id = e.currentTarget.dataset.id
    wx.navigateTo({
      url: '/pages/lyricDetail/lyricDetail?lyricId=' + id
    })
  }
})