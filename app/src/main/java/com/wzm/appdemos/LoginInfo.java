package com.wzm.appdemos;

/**
 * 类名：com.wzm.appdemos
 * 时间：2017/12/6 10:20
 * 描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 *
 * @author wangzm
 */

public class LoginInfo {

    /**
     * code : 0
     * msg : null
     * data : {"token":null,"name":null,"srsPath":null,"info":{"id":null,"name":null,"head_img":null,"gender":null,"birthdate":null,"post":null,"remark":null,"role_name":null,"group_name":null}}
     */

    private int code;
    private Object msg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * token : null
         * name : null
         * srsPath : null
         * info : {"id":null,"name":null,"head_img":null,"gender":null,"birthdate":null,"post":null,"remark":null,"role_name":null,"group_name":null}
         */

        private Object token;
        private Object name;
        private Object srsPath;
        private InfoBean info;

        public Object getToken() {
            return token;
        }

        public void setToken(Object token) {
            this.token = token;
        }

        public Object getName() {
            return name;
        }

        public void setName(Object name) {
            this.name = name;
        }

        public Object getSrsPath() {
            return srsPath;
        }

        public void setSrsPath(Object srsPath) {
            this.srsPath = srsPath;
        }

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public static class InfoBean {
            /**
             * id : null
             * name : null
             * head_img : null
             * gender : null
             * birthdate : null
             * post : null
             * remark : null
             * role_name : null
             * group_name : null
             */

            private Object id;
            private Object name;
            private Object head_img;
            private Object gender;
            private Object birthdate;
            private Object post;
            private Object remark;
            private Object role_name;
            private Object group_name;

            public Object getId() {
                return id;
            }

            public void setId(Object id) {
                this.id = id;
            }

            public Object getName() {
                return name;
            }

            public void setName(Object name) {
                this.name = name;
            }

            public Object getHead_img() {
                return head_img;
            }

            public void setHead_img(Object head_img) {
                this.head_img = head_img;
            }

            public Object getGender() {
                return gender;
            }

            public void setGender(Object gender) {
                this.gender = gender;
            }

            public Object getBirthdate() {
                return birthdate;
            }

            public void setBirthdate(Object birthdate) {
                this.birthdate = birthdate;
            }

            public Object getPost() {
                return post;
            }

            public void setPost(Object post) {
                this.post = post;
            }

            public Object getRemark() {
                return remark;
            }

            public void setRemark(Object remark) {
                this.remark = remark;
            }

            public Object getRole_name() {
                return role_name;
            }

            public void setRole_name(Object role_name) {
                this.role_name = role_name;
            }

            public Object getGroup_name() {
                return group_name;
            }

            public void setGroup_name(Object group_name) {
                this.group_name = group_name;
            }
        }
    }
}
