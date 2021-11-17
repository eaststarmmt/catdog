import Vue from "vue";
import Vuex from "vuex";
import http from "@/util/http-common.js";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    sidos: [{ value: null, text: "선택하세요" }],
    guguns: [{ value: null, text: "선택하세요" }],
    houses: [],
    house: null,
  },
  mutations: {
    SET_SIDO_LIST(state, sidos) {
      sidos.forEach((sido) => {
        state.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
      });
    },
    SET_GUGUN_LIST(state, guguns) {
      guguns.forEach((gugun) => {
        state.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
      });
    },
    CLEAR_SIDO_LIST(state) {
      state.sidos = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_GUGUN_LIST(state) {
      state.guguns = [{ value: null, text: "선택하세요" }];
    },
    SET_HOUSE_LIST(state, houses) {
      state.houses = houses;
    },
    SET_DETAIL_HOUSE(state, house) {
      state.house = house;
    },
  },
  actions: {
    // 첫번째 인자 값으로 context를 가져온다. commit. consol해서 ~할 수 있따. === 비동기 처리
    getSido({ commit }) {
      http
        .get(`/map/sido`)
        .then((response) => {
          // console.log(data); 배열형식
          commit("SET_SIDO_LIST", response.data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getGugun({ commit }, sidoCode) {
      const params = { sido: sidoCode };
      http
        .get(`/map/gugun`, { params })
        .then((response) => {
          // console.log(commit, response);
          commit("SET_GUGUN_LIST", response.data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getHouseList({ commit }, gugunCode) {
      const SERVICE_KEY = process.env.VUE_APP_APT_DEAL_API_KEY;
      // const SERVICE_KEY =
      //   "%2F8yx%2Bb7TfmUecq1x5rUIENwtehsTA4GEZYf5zdlKKFf7VXvEstqKj%2Frs7EHOY9LW8DJi6WV3iiQGKoPl%2BoQguA%3D%3D";
      const SERVICE_URL =
        "http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev";
      const params = {
        LAWD_CD: gugunCode,
        DEAL_YMD: "202110",
        serviceKey: decodeURIComponent(SERVICE_KEY),
      };
      http
        .get(SERVICE_URL, { params })
        .then((response) => {
          // console.log(SERVICE_URL);
          console.log(commit, response);
          commit("SET_HOUSE_LIST", response.data.response.body.items.item);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    detailHouse({ commit }, house) {
      // 나중에 house.일력번호를 이용하여 API 호출
      commit("SET_DETAIL_HOUSE", house);
    },
  },
  modules: {},
});
