package com.education.myoschinatest.bean;

import com.google.gson.annotations.SerializedName;
import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

@Table("room")
public  class RoomBean {
    /**
     * room_id : 993343
     * room_src : https://staticlive.douyucdn.cn/upload/appCovers/993343/20160830/e41ca9129697b310b1fd7c851a267ac6_small.jpg
     * vertical_src : https://staticlive.douyucdn.cn/upload/appCovers/993343/20160830/e41ca9129697b310b1fd7c851a267ac6_big.jpg
     * isVertical : 1
     * cate_id : 201
     * room_name : 畅儿Myc的直播间
     * show_status : 1
     * subject :
     * show_time : 1472542208
     * owner_uid : 66066798
     * specific_catalog :
     * specific_status : 0
     * vod_quality : 0
     * nickname : 畅儿Myc
     * online : 3834
     * game_name : 颜值
     * child_id : 0
     * ranktype : 0
     * show_type : 0
     * anchor_city : 鱼塘
     */
        @PrimaryKey(AssignType.AUTO_INCREMENT)
        private int id;
        @SerializedName("room_id")
        private String roomId;
        @SerializedName("room_src")
        private String roomSrc;
        @SerializedName("vertical_src")
        private String verticalSrc;
        @SerializedName("isVertical")
        private int isVertical;
        @SerializedName("cate_id")
        private String cateId;
        @SerializedName("room_name")
        private String roomName;
        @SerializedName("show_status")
        private String showStatus;
        @SerializedName("subject")
        private String subject;
        @SerializedName("show_time")
        private String showTime;
        @SerializedName("owner_uid")
        private String ownerUid;
        @SerializedName("specific_catalog")
        private String specificCatalog;
        @SerializedName("specific_status")
        private String specificStatus;
        @SerializedName("vod_quality")
        private String vodQuality;
        @SerializedName("nickname")
        private String nickname;
        @SerializedName("online")
        private int online;
        @SerializedName("game_name")
        private String gameName;
        @SerializedName("child_id")
        private String childId;
        @SerializedName("ranktype")
        private int ranktype;
        @SerializedName("show_type")
        private int showType;
        @SerializedName("anchor_city")
        private String anchorCity;

        public String getRoomId() {
            return roomId;
        }

        public void setRoomId(String roomId) {
            this.roomId = roomId;
        }

        public String getRoomSrc() {
            return roomSrc;
        }

        public void setRoomSrc(String roomSrc) {
            this.roomSrc = roomSrc;
        }

        public String getVerticalSrc() {
            return verticalSrc;
        }

        public void setVerticalSrc(String verticalSrc) {
            this.verticalSrc = verticalSrc;
        }

        public int getIsVertical() {
            return isVertical;
        }

        public void setIsVertical(int isVertical) {
            this.isVertical = isVertical;
        }

        public String getCateId() {
            return cateId;
        }

        public void setCateId(String cateId) {
            this.cateId = cateId;
        }

        public String getRoomName() {
            return roomName;
        }

        public void setRoomName(String roomName) {
            this.roomName = roomName;
        }

        public String getShowStatus() {
            return showStatus;
        }

        public void setShowStatus(String showStatus) {
            this.showStatus = showStatus;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getShowTime() {
            return showTime;
        }

        public void setShowTime(String showTime) {
            this.showTime = showTime;
        }

        public String getOwnerUid() {
            return ownerUid;
        }

        public void setOwnerUid(String ownerUid) {
            this.ownerUid = ownerUid;
        }

        public String getSpecificCatalog() {
            return specificCatalog;
        }

        public void setSpecificCatalog(String specificCatalog) {
            this.specificCatalog = specificCatalog;
        }

        public String getSpecificStatus() {
            return specificStatus;
        }

        public void setSpecificStatus(String specificStatus) {
            this.specificStatus = specificStatus;
        }

        public String getVodQuality() {
            return vodQuality;
        }

        public void setVodQuality(String vodQuality) {
            this.vodQuality = vodQuality;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getOnline() {
            return online;
        }

        public void setOnline(int online) {
            this.online = online;
        }

        public String getGameName() {
            return gameName;
        }

        public void setGameName(String gameName) {
            this.gameName = gameName;
        }

        public String getChildId() {
            return childId;
        }

        public void setChildId(String childId) {
            this.childId = childId;
        }

        public int getRanktype() {
            return ranktype;
        }

        public void setRanktype(int ranktype) {
            this.ranktype = ranktype;
        }

        public int getShowType() {
            return showType;
        }

        public void setShowType(int showType) {
            this.showType = showType;
        }

        public String getAnchorCity() {
            return anchorCity;
        }

        public void setAnchorCity(String anchorCity) {
            this.anchorCity = anchorCity;
        }
    }