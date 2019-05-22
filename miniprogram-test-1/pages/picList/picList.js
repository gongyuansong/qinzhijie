Page({
  data: {
    picList: []
  },
  onLoad: function(event) {
    var that = this
    wx.request({
      url: 'https://www.gysp.top:8081/bookDetail/getPicList',
      method: 'POST',
      data: {
        currentPage: "1",
        pageSize: "9999",
        queryObj: {
          id: event.bookDetailId
        }
      },
      success: function(res) {
        if (res.data.data !== null) {
          that.setData({
            picList: res.data.data.dataList[0].allPath.replace(/\\/g,"//").split(";")
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