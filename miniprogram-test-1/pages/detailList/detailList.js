//logs.js
const util = require('../../utils/util.js')

Page({
  data: {
    bookDetailList: []
  },

  onLoad: function() {
    var that = this
    wx.request({
      url: 'https://127.0.0.1:8081/bookDetail/getList',
      method: 'POST',
      data: {
        currentPage: "1",
        pageSize: "9999",
        queryObj: {
          bookId: 157
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

        if ('pages/detailList/detailList' !== curPages) {
          wx.navigateTo({
            url: '../detailList/detailList'
          })
        }
      }
    })  
  }
})