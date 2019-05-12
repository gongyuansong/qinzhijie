//index.js
//获取应用实例
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
      'http://47.105.212.81:8082/qinzhijie/images/swiper/1.jpg',
      'http://47.105.212.81:8082/qinzhijie/images/swiper/2.jpg',
      'http://47.105.212.81:8082/qinzhijie/images/swiper/3.jpg',
      'http://47.105.212.81:8082/qinzhijie/images/swiper/4.jpg',
      'http://47.105.212.81:8082/qinzhijie/images/swiper/5.jpg',
      'http://47.105.212.81:8082/qinzhijie/images/swiper/6.jpg'
    ],
    current: 0,
  },  
  swiperChange: function (e) {
    var that = this;
    if (e.detail.source == 'touch') {
      that.setData({
        current: e.detail.current,

      })
    }
  },
  onLoad: function (options) {

  },
  onShow: function () {

  },

  //搜索框文本内容显示
  inputBind: function (event) {
    this.setData({
      inputValue: event.detail.value
    })
    console.log('bindInput' + this.data.inputValue)

  },
  /**
  * 搜索执行按钮
  */
  query: function (event) {
    console.log(this.data.inputValue)
    var that = this
    wx.request({
      url: 'https://127.0.0.1:8081/station/search',
      method: 'POST',
      header: { 'content-type': 'application/x-www-form-urlencoded' },
      data: {
        queryStr: this.data.inputValue
      },
      success: function (res) {
        console.log(res.data)
        var searchData = res.data
        if(res.data.data !== null){
          
          console.log(res.data.data)
        }
        wx.navigateTo({
          url: '../search/search'
        })
        // that.setData({
        //   swiperShowView: false
        // })

        // /**
        //  * 把 从get_issue_searchAPI 
        //  * 获取 提问帖子搜索 的数据 设置缓存
        //  */
        // wx.setStorage({
        //   key: 'searchLists',
        //   data: {
        //     searchLists: res.data
        //   }
        // })

        /**
         * 设置 模糊搜索
         */

      }
    })
  }
})
