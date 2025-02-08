import { createStore } from 'vuex';

export default createStore({
    state: {
        error: null,
    },
    mutations: {
        setError(state, error) {
            state.error = error;
        },
        clearError(state) {
            state.error = null;
        },
    },
    actions: {
        triggerError({ commit }, error) {
            commit('setError', error);
            setTimeout(() => {
                commit('clearError');
            }, 5000);
        },
    },
});
