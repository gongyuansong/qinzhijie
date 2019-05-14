Page({
  data: {
    picList: []
  },
  onLoad: function(event) {
    console.log(event.bookDetailId + "AAAAAAAAAAAAAAAAAAAAAAAAA")
    var that = this
    wx.request({
      url: 'https://127.0.0.1:8081/bookDetail/getList',
      method: 'POST',
      data: {
        currentPage: "1",
        pageSize: "9999",
        queryObj: {
          id: event.bookDetailId
        }
      },
      success: function(res) {
        console.log(res)
        if (res.data.data !== null) {
          that.setData({
            picList: res.data.data.dataList[0].allPath.replace(/\\/g,"//").split(";")
          })
        }
  
        var pages = getCurrentPages()
        var curPages = pages[pages.length - 1].route
        console.log(curPages)

        if ('pages/picList/picList' !== curPages) {
          wx.navigateTo({
            url: '../picList/picList'
          })
        }
      }
    })  
  },
  showPic(e){
    wx.navigateTo({
      url: '../pic/pic?url=' + e.currentTarget.dataset.str
    })
  }
})