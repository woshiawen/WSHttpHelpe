package com.example.wsapplication;

import java.util.List;

public class BannerEntity {

  /**
   * code : 0
   * data : [{"idString":"0","url":"https://t12.baidu.com/it/u=2405198363,3596091896&fm=76","type":"0","title":"淘宝","describe":"描述1"},{"idString":"1","url":"https://img-ads.csdn.net/2019/201904191711226513.png","type":"0","title":"京东","describe":"描述1"}]
   * message : 成功!
   */

  private int code;
  private String message;
  private List<DataBean> data;

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public List<DataBean> getData() {
    return data;
  }

  public void setData(List<DataBean> data) {
    this.data = data;
  }

  public static class DataBean {
    /**
     * idString : 0
     * url : https://t12.baidu.com/it/u=2405198363,3596091896&fm=76
     * type : 0
     * title : 淘宝
     * describe : 描述1
     */

    private String idString;
    private String url;
    private String type;
    private String title;
    private String describe;

    public String getIdString() {
      return idString;
    }

    public void setIdString(String idString) {
      this.idString = idString;
    }

    public String getUrl() {
      return url;
    }

    public void setUrl(String url) {
      this.url = url;
    }

    public String getType() {
      return type;
    }

    public void setType(String type) {
      this.type = type;
    }

    public String getTitle() {
      return title;
    }

    public void setTitle(String title) {
      this.title = title;
    }

    public String getDescribe() {
      return describe;
    }

    public void setDescribe(String describe) {
      this.describe = describe;
    }
  }
}
