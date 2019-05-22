const app = getApp()

Page({
  data: {
    motto: 'Hello World',
    indicatorDots: false,
    autoplay: true,
    interval: 2000,
    swiperShowView: true,

    duration: '500',
    imgUrls: [
      'http://www.gysp.top:8082/qinzhijie/images/swiper/1.jpg',
      'http://www.gysp.top:8082/qinzhijie/images/swiper/2.jpg',
      'http://www.gysp.top:8082/qinzhijie/images/swiper/3.jpg',
      'http://www.gysp.top:8082/qinzhijie/images/swiper/4.jpg',
      'http://www.gysp.top:8082/qinzhijie/images/swiper/5.jpg',
      'http://www.gysp.top:8082/qinzhijie/images/swiper/6.jpg'
    ],
    current: 0,
  },
  swiperChange: function(e) {
    var that = this;
    if (e.detail.source == 'touch') {
      that.setData({
        current: e.detail.current,

      })
    }
  },
  onLoad: function(options) {
  },
  onShow: function() {
  },

  //搜索框文本内容显示
  inputBind: function(event) {
    this.setData({
      inputValue: event.detail.value
    })
  },
  /**
   * 搜索执行按钮
   */
  query: function(event) {
    var that = this
    wx.navigateTo({
      url: '../search/search?queryStr=' + this.data.inputValue
    })
  }
})