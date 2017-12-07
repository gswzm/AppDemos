package com.wzm.appdemos;

import java.io.Serializable;
import java.util.List;

/**
 * 类名：com.wzm.appdemos
 * 时间：2017/12/6 11:28
 * 描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 *
 * @author wangzm
 */

public class AccountInfo implements Serializable{

    /**
     * msg : null
     * code : 200
     * data : {"address":"admin","block":[{"address":"甘肃省兰州市七里河区南滨河中路","block_acreage":400,"block_create_time":"2017-07-26 16:29:29","block_id":"9dddf26071dc11e7ae6efcaa145e7e0f","block_name":"三号田","create_time":"2017-10-17 16:23:30","description":"葡萄xxxxxxx","id":"75425346b31411e7a0c7fcaa145e7e0f","member_id":"99aaa63f71d711e7ae6efcaa145e7e0f","member_mobile":"1110","member_name":"马有有","pass":"-1,1,899232e870db11e7ae6efcaa145e7e0f","status":"0"},{"address":"甘肃省兰州市七里河区五星坪后街","block_acreage":100,"block_create_time":"2017-07-24 13:35:21","block_id":"f26b396d703111e7ae6efcaa145e7e0f","block_name":"一号田","create_time":"2017-10-17 16:23:30","description":"水稻，是一年生禾本科植物，高约1.2米，叶长而扁，圆锥花序由许多小穗组成。所结子实即稻谷，去壳后称大米或米。世界上近一半人口，水稻可以分为籼稻和粳稻、早稻和中晚稻、糯稻和非糯稻。水稻所结稻粒去壳后称大米或米。水稻除可食用外，还可以酿酒、制糖作工业原料，稻壳、稻秆也有很多用处。水稻属于禾本科稻属，是一个极其古老的作物。据考古发现，水稻在我国的种植历史至少有7000年左右。世界上的栽培稻有2个种即亚洲栽培稻和非洲栽培稻。其中亚洲栽培稻种植面积大，遍布全球各稻区，所以称之为普通栽培稻。大量事实证明，我国南方至少是普通栽培稻的起源中心之一。水稻经长期进化和不同生态条件的再塑造便发生了分化，我国学者丁颖(1957)根据对中国栽培稻(属亚洲栽培稻)的起源、演变和有关古籍的研究认定，中国栽培稻可分成籼、粳两个亚种，并根据品种的温光反应，需水量及胚乳淀粉特性等在籼、粳亚种下又分为早、晚，水、陆，粘(非糯)、糯等不同类型。所以严格地说，把籼、粳和糯稻并列在分类上是不确切的，实际上籼、粳亚种都有粘与糯之分，如下所示：糯性\u2014\u2014长粒糯(小糯)籼稻非糯性\u2014\u2014釉稻稻谷糯性\u2014\u2014团粒糯(大糯)粳稻非糯性\u2014\u2014粳稻籼稻的性状比较接近于其祖先野生稻，所以有学者认为籼稻未基本型，粳稻未变异型。籼稻适宜于在低纬度、低海拔湿热地区种植。","id":"7542831ab31411e7a0c7fcaa145e7e0f","member_id":"99aaa63f71d711e7ae6efcaa145e7e0f","member_mobile":"1110","member_name":"马有有","pass":"-1,1,899232e870db11e7ae6efcaa145e7e0f","status":"0"},{"address":"甘肃省兰州市七里河区","block_acreage":300,"block_create_time":"2017-07-22 17:04:17","block_id":"ca0ed42d6ebc11e7ae6efcaa145e7e0f","block_name":"二号田","create_time":"2017-10-17 16:23:30","description":"大葵xxx","id":"75429740b31411e7a0c7fcaa145e7e0f","member_id":"99aaa63f71d711e7ae6efcaa145e7e0f","member_mobile":"1110","member_name":"马有有","pass":"-1,1,899232e870db11e7ae6efcaa145e7e0f","status":"0"}],"create_by":null,"create_time":"2017-07-26 15:53:35","enabled":"0","head_portrait":"40288a525f9f4d49015fec3dd16700c5","id":"99aaa63f71d711e7ae6efcaa145e7e0f","login_account":"mayy","login_pwd":null,"mains":["11-22-33-44-55-01"],"mobile":"1110","name":"马有有","org_id":"899232e870db11e7ae6efcaa145e7e0f","qr_code":"4f3e32dd46fba525f718c17a4d3edaa0","salt":null,"status":"0","uuid":"af44951278654eefb5a662b1e068dc2e"}
     */

    private Object msg;
    private int code;
    private DataBean data;

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * address : admin
         * block : [{"address":"甘肃省兰州市七里河区南滨河中路","block_acreage":400,"block_create_time":"2017-07-26 16:29:29","block_id":"9dddf26071dc11e7ae6efcaa145e7e0f","block_name":"三号田","create_time":"2017-10-17 16:23:30","description":"葡萄xxxxxxx","id":"75425346b31411e7a0c7fcaa145e7e0f","member_id":"99aaa63f71d711e7ae6efcaa145e7e0f","member_mobile":"1110","member_name":"马有有","pass":"-1,1,899232e870db11e7ae6efcaa145e7e0f","status":"0"},{"address":"甘肃省兰州市七里河区五星坪后街","block_acreage":100,"block_create_time":"2017-07-24 13:35:21","block_id":"f26b396d703111e7ae6efcaa145e7e0f","block_name":"一号田","create_time":"2017-10-17 16:23:30","description":"水稻，是一年生禾本科植物，高约1.2米，叶长而扁，圆锥花序由许多小穗组成。所结子实即稻谷，去壳后称大米或米。世界上近一半人口，水稻可以分为籼稻和粳稻、早稻和中晚稻、糯稻和非糯稻。水稻所结稻粒去壳后称大米或米。水稻除可食用外，还可以酿酒、制糖作工业原料，稻壳、稻秆也有很多用处。水稻属于禾本科稻属，是一个极其古老的作物。据考古发现，水稻在我国的种植历史至少有7000年左右。世界上的栽培稻有2个种即亚洲栽培稻和非洲栽培稻。其中亚洲栽培稻种植面积大，遍布全球各稻区，所以称之为普通栽培稻。大量事实证明，我国南方至少是普通栽培稻的起源中心之一。水稻经长期进化和不同生态条件的再塑造便发生了分化，我国学者丁颖(1957)根据对中国栽培稻(属亚洲栽培稻)的起源、演变和有关古籍的研究认定，中国栽培稻可分成籼、粳两个亚种，并根据品种的温光反应，需水量及胚乳淀粉特性等在籼、粳亚种下又分为早、晚，水、陆，粘(非糯)、糯等不同类型。所以严格地说，把籼、粳和糯稻并列在分类上是不确切的，实际上籼、粳亚种都有粘与糯之分，如下所示：糯性\u2014\u2014长粒糯(小糯)籼稻非糯性\u2014\u2014釉稻稻谷糯性\u2014\u2014团粒糯(大糯)粳稻非糯性\u2014\u2014粳稻籼稻的性状比较接近于其祖先野生稻，所以有学者认为籼稻未基本型，粳稻未变异型。籼稻适宜于在低纬度、低海拔湿热地区种植。","id":"7542831ab31411e7a0c7fcaa145e7e0f","member_id":"99aaa63f71d711e7ae6efcaa145e7e0f","member_mobile":"1110","member_name":"马有有","pass":"-1,1,899232e870db11e7ae6efcaa145e7e0f","status":"0"},{"address":"甘肃省兰州市七里河区","block_acreage":300,"block_create_time":"2017-07-22 17:04:17","block_id":"ca0ed42d6ebc11e7ae6efcaa145e7e0f","block_name":"二号田","create_time":"2017-10-17 16:23:30","description":"大葵xxx","id":"75429740b31411e7a0c7fcaa145e7e0f","member_id":"99aaa63f71d711e7ae6efcaa145e7e0f","member_mobile":"1110","member_name":"马有有","pass":"-1,1,899232e870db11e7ae6efcaa145e7e0f","status":"0"}]
         * create_by : null
         * create_time : 2017-07-26 15:53:35
         * enabled : 0
         * head_portrait : 40288a525f9f4d49015fec3dd16700c5
         * id : 99aaa63f71d711e7ae6efcaa145e7e0f
         * login_account : mayy
         * login_pwd : null
         * mains : ["11-22-33-44-55-01"]
         * mobile : 1110
         * name : 马有有
         * org_id : 899232e870db11e7ae6efcaa145e7e0f
         * qr_code : 4f3e32dd46fba525f718c17a4d3edaa0
         * salt : null
         * status : 0
         * uuid : af44951278654eefb5a662b1e068dc2e
         */

        private String address;
        private Object create_by;
        private String create_time;
        private String enabled;
        private String head_portrait;
        private String id;
        private String login_account;
        private Object login_pwd;
        private String mobile;
        private String name;
        private String org_id;
        private String qr_code;
        private Object salt;
        private String status;
        private String uuid;
        private List<BlockBean> block;
        private List<String> mains;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Object getCreate_by() {
            return create_by;
        }

        public void setCreate_by(Object create_by) {
            this.create_by = create_by;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getEnabled() {
            return enabled;
        }

        public void setEnabled(String enabled) {
            this.enabled = enabled;
        }

        public String getHead_portrait() {
            return head_portrait;
        }

        public void setHead_portrait(String head_portrait) {
            this.head_portrait = head_portrait;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLogin_account() {
            return login_account;
        }

        public void setLogin_account(String login_account) {
            this.login_account = login_account;
        }

        public Object getLogin_pwd() {
            return login_pwd;
        }

        public void setLogin_pwd(Object login_pwd) {
            this.login_pwd = login_pwd;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOrg_id() {
            return org_id;
        }

        public void setOrg_id(String org_id) {
            this.org_id = org_id;
        }

        public String getQr_code() {
            return qr_code;
        }

        public void setQr_code(String qr_code) {
            this.qr_code = qr_code;
        }

        public Object getSalt() {
            return salt;
        }

        public void setSalt(Object salt) {
            this.salt = salt;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public List<BlockBean> getBlock() {
            return block;
        }

        public void setBlock(List<BlockBean> block) {
            this.block = block;
        }

        public List<String> getMains() {
            return mains;
        }

        public void setMains(List<String> mains) {
            this.mains = mains;
        }

        public static class BlockBean {
            /**
             * address : 甘肃省兰州市七里河区南滨河中路
             * block_acreage : 400
             * block_create_time : 2017-07-26 16:29:29
             * block_id : 9dddf26071dc11e7ae6efcaa145e7e0f
             * block_name : 三号田
             * create_time : 2017-10-17 16:23:30
             * description : 葡萄xxxxxxx
             * id : 75425346b31411e7a0c7fcaa145e7e0f
             * member_id : 99aaa63f71d711e7ae6efcaa145e7e0f
             * member_mobile : 1110
             * member_name : 马有有
             * pass : -1,1,899232e870db11e7ae6efcaa145e7e0f
             * status : 0
             */

            private String address;
            private int block_acreage;
            private String block_create_time;
            private String block_id;
            private String block_name;
            private String create_time;
            private String description;
            private String id;
            private String member_id;
            private String member_mobile;
            private String member_name;
            private String pass;
            private String status;

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public int getBlock_acreage() {
                return block_acreage;
            }

            public void setBlock_acreage(int block_acreage) {
                this.block_acreage = block_acreage;
            }

            public String getBlock_create_time() {
                return block_create_time;
            }

            public void setBlock_create_time(String block_create_time) {
                this.block_create_time = block_create_time;
            }

            public String getBlock_id() {
                return block_id;
            }

            public void setBlock_id(String block_id) {
                this.block_id = block_id;
            }

            public String getBlock_name() {
                return block_name;
            }

            public void setBlock_name(String block_name) {
                this.block_name = block_name;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getMember_id() {
                return member_id;
            }

            public void setMember_id(String member_id) {
                this.member_id = member_id;
            }

            public String getMember_mobile() {
                return member_mobile;
            }

            public void setMember_mobile(String member_mobile) {
                this.member_mobile = member_mobile;
            }

            public String getMember_name() {
                return member_name;
            }

            public void setMember_name(String member_name) {
                this.member_name = member_name;
            }

            public String getPass() {
                return pass;
            }

            public void setPass(String pass) {
                this.pass = pass;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }
        }
    }
}
