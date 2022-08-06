// 查询列表接口
const getCotaskPage = (params) => {
    return $axios({
        url: '/cotasking/getPage',
        method: 'get',
        params
    })
}
// 删除接口
const deleteById = (ids) => {
    return $axios({
        url: '/cotasking/delete',
        method: 'delete',
        // get会将请求参数拼接在url上
        params: {ids}
    })
}
// 获取协同任务详细信息
const getCotaskById = (id) => {
    return $axios({
        url: '/cotasking/getCotaskById/' + id,
        method: 'get',
    })
}
// 根据协同任务id获取当前协调任务包含的情报
const getIntelligencesInCotask = (id) => {
    return $axios({
        url: "/cotasking-intelligence/getIntelligencesInCotask/" + id,
        method: "get"
    })
}