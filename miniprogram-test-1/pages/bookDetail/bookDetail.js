
Page({
  data: {
    bookDetailList: [],
    bookId: ''
  },
  onLoad: function(event) {
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
      url: 'https://127.0.0.1:8081/bookDetail/getList',
      method: 'POST',
      data: {
        currentPage: "1",
        pageSize: "9999",
        queryObj: {
          partName: this.data.inputValue
        }
      },
      success: function(res) {
        console.log(res)
        var searchData = res.data
        if (res.data.data !== null) {
          that.setData({
            bookDetailList: res.data.data.dataList
          })
        }

        var pages = getCurrentPages()
        var curPages = pages[pages.length - 1].route
        console.log(curPages)

        if ('pages/bookDetail/bookDetail' !== curPages) {
          wx.navigateTo({
            url: '../bookDetail/bookDetail'
          })
        }
      }
    })  
  }
})