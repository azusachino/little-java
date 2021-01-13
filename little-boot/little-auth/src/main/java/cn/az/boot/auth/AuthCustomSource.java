package cn.az.boot.auth;

import me.zhyd.oauth.config.AuthSource;

/**
 * The enum Auth custom source.
 *
 * @author Liz
 */
public enum AuthCustomSource implements AuthSource {
    /**
     * The Gitlab.
     */
    GITLAB {
        /**
         * 授权的api
         *
         * @return url
         */
        @Override
        public String authorize() {
            return "http://gitlab.innodev.cn:9001/oauth/authorize";

        }

        /**
         * 获取accessToken的api
         *
         * @return url
         */
        @Override
        public String accessToken() {
            return "http://gitlab.innodev.cn:9001/oauth/token";
        }

        /**
         * 获取用户信息的api
         *
         * @return url
         */
        @Override
        public String userInfo() {
            return "http://gitlab.innodev.cn:9001/api/v4/user";
        }
    },
    /**
     * The Github.
     */
    GITHUB {
        /**
         * 授权的api
         *
         * @return url
         */
        @Override
        public String authorize() {
            return null;
        }

        /**
         * 获取accessToken的api
         *
         * @return url
         */
        @Override
        public String accessToken() {
            return null;
        }

        /**
         * 获取用户信息的api
         *
         * @return url
         */
        @Override
        public String userInfo() {
            return null;
        }
    };
}
