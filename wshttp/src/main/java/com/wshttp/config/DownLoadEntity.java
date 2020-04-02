package com.wshttp.config;

import java.io.File;

public class DownLoadEntity {

  private String path;
  private int pro;  //进度
  private long size;
  private File  downLoadFile; //下载地址


  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public int getPro() {
    return pro;
  }

  public void setPro(int pro) {
    this.pro = pro;
  }

  public long getSize() {
    return size;
  }

  public void setSize(long size) {
    this.size = size;
  }

  public File getDownLoadFile() {
    return downLoadFile;
  }

  public void setDownLoadFile(File downLoadFile) {
    this.downLoadFile = downLoadFile;
  }
}
