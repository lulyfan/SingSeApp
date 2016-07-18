package com.yxb.android.shengseshequ.model;

import java.util.List;

/**
 * Created by acer on 2016/7/17.
 */
public class Forum {

    /**
     * Version : 5
     * Charset : GBK
     * Variables : {"cookiepre":"zIN0_d45e_","auth":null,"saltkey":"QC661bCn","member_uid":"0","member_username":"","member_avatar":"http://www.singse.com/uc_server/avatar.php?uid=0&size=small","groupid":"7","formhash":"94abc2ef","ismoderator":null,"readaccess":"1","notice":{"newpush":"0","newpm":"0","newprompt":"0","newmypost":"0"},"summary":{"posts":"2831169","bbsnewposts":"235","members":"106246"},"hot_forum_list":[{"title":"侨乡聊吧","fid":"29","posts":"1673464","icon":"http://attachment.singse.com/common/6e/common_29_icon.png"},{"title":"民声关注","fid":"93","posts":"415279","icon":"http://attachment.singse.com/common/98/common_93_icon.png"},{"title":"我爱摄影","fid":"367","posts":"100078","icon":"http://attachment.singse.com/common/05/common_367_icon.png"},{"title":"饮饮食食","fid":"264","posts":"96291","icon":"http://attachment.singse.com/common/d6/common_264_icon.png"},{"title":"二手市场","fid":"359","posts":"40081","icon":"http://attachment.singse.com/common/c0/common_359_icon.png"},{"title":"非诚勿扰","fid":"261","posts":"23709","icon":"http://attachment.singse.com/common/b1/common_261_icon.png"}],"forumlist":[{"fid":"29","name":"侨乡聊吧","todayposts":"154"},{"fid":"359","name":"二手市场","todayposts":"1"},{"fid":"93","name":"民声关注","todayposts":"59","sublist":[{"fid":"362","name":"每日签到","threads":"1830","posts":"186021","todayposts":"23"}]},{"fid":"283","name":"房屋租售","todayposts":"0"},{"fid":"315","name":"车迷天地","todayposts":"0"},{"fid":"198","name":"招聘求职","todayposts":"2"},{"fid":"367","name":"我爱摄影","todayposts":"5"},{"fid":"384","name":"婚姻亲子","todayposts":"22"},{"fid":"261","name":"非诚勿扰","todayposts":"0"},{"fid":"263","name":"靓照show","todayposts":"3"},{"fid":"312","name":"爱宠家园","todayposts":"0"},{"fid":"288","name":"便民服务","todayposts":"0"},{"fid":"264","name":"饮饮食食","todayposts":"6","sublist":[{"fid":"400","name":"美食专栏","threads":"38","posts":"41","todayposts":"0"}]},{"fid":"92","name":"江门网购","todayposts":"0"},{"fid":"311","name":"谈婚论嫁","todayposts":"0"},{"fid":"23","name":"网友活动","todayposts":"1"},{"fid":"200","name":"站务公告","todayposts":"0"},{"fid":"313","name":"寻找好店","todayposts":"0"},{"fid":"398","name":"旅行天下","todayposts":"0"}],"catlist":[{"fid":"252","name":"同城信息","forums":["359","283","198","261","288","92"]},{"fid":"14","name":"互动专区","forums":["29","93","367","384","263","264","311","23","200","313","398"]},{"fid":"12","name":"生活爱好","forums":["315","312"]}]}
     */

    private String Version;
    private String Charset;
    /**
     * cookiepre : zIN0_d45e_
     * auth : null
     * saltkey : QC661bCn
     * member_uid : 0
     * member_username :
     * member_avatar : http://www.singse.com/uc_server/avatar.php?uid=0&size=small
     * groupid : 7
     * formhash : 94abc2ef
     * ismoderator : null
     * readaccess : 1
     * notice : {"newpush":"0","newpm":"0","newprompt":"0","newmypost":"0"}
     * summary : {"posts":"2831169","bbsnewposts":"235","members":"106246"}
     * hot_forum_list : [{"title":"侨乡聊吧","fid":"29","posts":"1673464","icon":"http://attachment.singse.com/common/6e/common_29_icon.png"},{"title":"民声关注","fid":"93","posts":"415279","icon":"http://attachment.singse.com/common/98/common_93_icon.png"},{"title":"我爱摄影","fid":"367","posts":"100078","icon":"http://attachment.singse.com/common/05/common_367_icon.png"},{"title":"饮饮食食","fid":"264","posts":"96291","icon":"http://attachment.singse.com/common/d6/common_264_icon.png"},{"title":"二手市场","fid":"359","posts":"40081","icon":"http://attachment.singse.com/common/c0/common_359_icon.png"},{"title":"非诚勿扰","fid":"261","posts":"23709","icon":"http://attachment.singse.com/common/b1/common_261_icon.png"}]
     * forumlist : [{"fid":"29","name":"侨乡聊吧","todayposts":"154"},{"fid":"359","name":"二手市场","todayposts":"1"},{"fid":"93","name":"民声关注","todayposts":"59","sublist":[{"fid":"362","name":"每日签到","threads":"1830","posts":"186021","todayposts":"23"}]},{"fid":"283","name":"房屋租售","todayposts":"0"},{"fid":"315","name":"车迷天地","todayposts":"0"},{"fid":"198","name":"招聘求职","todayposts":"2"},{"fid":"367","name":"我爱摄影","todayposts":"5"},{"fid":"384","name":"婚姻亲子","todayposts":"22"},{"fid":"261","name":"非诚勿扰","todayposts":"0"},{"fid":"263","name":"靓照show","todayposts":"3"},{"fid":"312","name":"爱宠家园","todayposts":"0"},{"fid":"288","name":"便民服务","todayposts":"0"},{"fid":"264","name":"饮饮食食","todayposts":"6","sublist":[{"fid":"400","name":"美食专栏","threads":"38","posts":"41","todayposts":"0"}]},{"fid":"92","name":"江门网购","todayposts":"0"},{"fid":"311","name":"谈婚论嫁","todayposts":"0"},{"fid":"23","name":"网友活动","todayposts":"1"},{"fid":"200","name":"站务公告","todayposts":"0"},{"fid":"313","name":"寻找好店","todayposts":"0"},{"fid":"398","name":"旅行天下","todayposts":"0"}]
     * catlist : [{"fid":"252","name":"同城信息","forums":["359","283","198","261","288","92"]},{"fid":"14","name":"互动专区","forums":["29","93","367","384","263","264","311","23","200","313","398"]},{"fid":"12","name":"生活爱好","forums":["315","312"]}]
     */

    private VariablesBean Variables;

    public String getVersion() {
        return Version;
    }

    public void setVersion(String Version) {
        this.Version = Version;
    }

    public String getCharset() {
        return Charset;
    }

    public void setCharset(String Charset) {
        this.Charset = Charset;
    }

    public VariablesBean getVariables() {
        return Variables;
    }

    public void setVariables(VariablesBean Variables) {
        this.Variables = Variables;
    }

    public static class VariablesBean {
        private String cookiepre;
        private Object auth;
        private String saltkey;
        private String member_uid;
        private String member_username;
        private String member_avatar;
        private String groupid;
        private String formhash;
        private Object ismoderator;
        private String readaccess;
        /**
         * newpush : 0
         * newpm : 0
         * newprompt : 0
         * newmypost : 0
         */

        private NoticeBean notice;
        /**
         * posts : 2831169
         * bbsnewposts : 235
         * members : 106246
         */

        private SummaryBean summary;
        /**
         * title : 侨乡聊吧
         * fid : 29
         * posts : 1673464
         * icon : http://attachment.singse.com/common/6e/common_29_icon.png
         */

        private List<HotForumListBean> hot_forum_list;
        /**
         * fid : 29
         * name : 侨乡聊吧
         * todayposts : 154
         */

        private List<ForumlistBean> forumlist;
        /**
         * fid : 252
         * name : 同城信息
         * forums : ["359","283","198","261","288","92"]
         */

        private List<CatlistBean> catlist;

        public String getCookiepre() {
            return cookiepre;
        }

        public void setCookiepre(String cookiepre) {
            this.cookiepre = cookiepre;
        }

        public Object getAuth() {
            return auth;
        }

        public void setAuth(Object auth) {
            this.auth = auth;
        }

        public String getSaltkey() {
            return saltkey;
        }

        public void setSaltkey(String saltkey) {
            this.saltkey = saltkey;
        }

        public String getMember_uid() {
            return member_uid;
        }

        public void setMember_uid(String member_uid) {
            this.member_uid = member_uid;
        }

        public String getMember_username() {
            return member_username;
        }

        public void setMember_username(String member_username) {
            this.member_username = member_username;
        }

        public String getMember_avatar() {
            return member_avatar;
        }

        public void setMember_avatar(String member_avatar) {
            this.member_avatar = member_avatar;
        }

        public String getGroupid() {
            return groupid;
        }

        public void setGroupid(String groupid) {
            this.groupid = groupid;
        }

        public String getFormhash() {
            return formhash;
        }

        public void setFormhash(String formhash) {
            this.formhash = formhash;
        }

        public Object getIsmoderator() {
            return ismoderator;
        }

        public void setIsmoderator(Object ismoderator) {
            this.ismoderator = ismoderator;
        }

        public String getReadaccess() {
            return readaccess;
        }

        public void setReadaccess(String readaccess) {
            this.readaccess = readaccess;
        }

        public NoticeBean getNotice() {
            return notice;
        }

        public void setNotice(NoticeBean notice) {
            this.notice = notice;
        }

        public SummaryBean getSummary() {
            return summary;
        }

        public void setSummary(SummaryBean summary) {
            this.summary = summary;
        }

        public List<HotForumListBean> getHot_forum_list() {
            return hot_forum_list;
        }

        public void setHot_forum_list(List<HotForumListBean> hot_forum_list) {
            this.hot_forum_list = hot_forum_list;
        }

        public List<ForumlistBean> getForumlist() {
            return forumlist;
        }

        public void setForumlist(List<ForumlistBean> forumlist) {
            this.forumlist = forumlist;
        }

        public List<CatlistBean> getCatlist() {
            return catlist;
        }

        public void setCatlist(List<CatlistBean> catlist) {
            this.catlist = catlist;
        }

        public static class NoticeBean {
            private String newpush;
            private String newpm;
            private String newprompt;
            private String newmypost;

            public String getNewpush() {
                return newpush;
            }

            public void setNewpush(String newpush) {
                this.newpush = newpush;
            }

            public String getNewpm() {
                return newpm;
            }

            public void setNewpm(String newpm) {
                this.newpm = newpm;
            }

            public String getNewprompt() {
                return newprompt;
            }

            public void setNewprompt(String newprompt) {
                this.newprompt = newprompt;
            }

            public String getNewmypost() {
                return newmypost;
            }

            public void setNewmypost(String newmypost) {
                this.newmypost = newmypost;
            }
        }

        public static class SummaryBean {
            private String posts;
            private String bbsnewposts;
            private String members;

            public String getPosts() {
                return posts;
            }

            public void setPosts(String posts) {
                this.posts = posts;
            }

            public String getBbsnewposts() {
                return bbsnewposts;
            }

            public void setBbsnewposts(String bbsnewposts) {
                this.bbsnewposts = bbsnewposts;
            }

            public String getMembers() {
                return members;
            }

            public void setMembers(String members) {
                this.members = members;
            }
        }

        public static class HotForumListBean {
            private String title;
            private String fid;
            private String posts;
            private String icon;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getFid() {
                return fid;
            }

            public void setFid(String fid) {
                this.fid = fid;
            }

            public String getPosts() {
                return posts;
            }

            public void setPosts(String posts) {
                this.posts = posts;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }
        }

        public static class ForumlistBean {
            private String fid;
            private String name;
            private String todayposts;

            public String getFid() {
                return fid;
            }

            public void setFid(String fid) {
                this.fid = fid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTodayposts() {
                return todayposts;
            }

            public void setTodayposts(String todayposts) {
                this.todayposts = todayposts;
            }
        }

        public static class CatlistBean {
            private String fid;
            private String name;
            private List<String> forums;

            public String getFid() {
                return fid;
            }

            public void setFid(String fid) {
                this.fid = fid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<String> getForums() {
                return forums;
            }

            public void setForums(List<String> forums) {
                this.forums = forums;
            }
        }
    }
}
