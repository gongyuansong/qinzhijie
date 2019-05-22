Page({
  data: {
    lyricDetail: '',
    lyricList: []
      },
  //搜索框文本内容显示
  inputBind: function (event) {
    this.setData({
      inputValue: event.detail.value
    })
  },
  onLoad: function (event) {
    var that = this
    wx.request({
      url: 'https://www.gysp.top:8081/bookLyric/getList',
      method: 'POST',
      data: {
        currentPage: "1",
        pageSize: "1",
        queryObj: {
          id: event.lyricId
        }
      },
      success: function (res) {
        if (res.data.data !== null) {
          that.setData({
            lyricDetail: res.data.data.dataList,
            lyricList: []
          })
        }
      }
    }) 
  },
  query: function (event) {
    var that = this
    wx.request({
      url: 'https://www.gysp.top:8081/bookLyric/getList',
      method: 'POST',
      data: {
        currentPage: "1",
        pageSize: "9999",
        queryObj: {
          musicofviolin: this.data.inputValue
        }
      },
      success: function (res) {
        if (res.data.data !== null) {
          that.setData({
            lyricDetail: '',
            lyricList: res.data.data.dataList
          })
        }
      }
    })
  },
  showLyricDetail(e) {
    var that = this
    var id = e.currentTarget.dataset.id
    wx.request({
      url: 'https://www.gysp.top:8081/bookLyric/getList',
      method: 'POST',
      data: {
        currentPage: "1",
        pageSize: "9999",
        queryObj: {
          id: id
        }
      },
      success: function (res) {
        if (res.data.data !== null) {
          that.setData({
            lyricDetail: res.data.data.dataList,
            lyricList: ''
          })
        }
      }
    })
  }
})