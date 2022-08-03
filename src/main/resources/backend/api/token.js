// 查询列表接口
const getTokenPage = (params) => {
    return $axios({
        url: '/token/page',
        method: 'get',
        params
    })
}

// 获取用户列表
const getUserList = (params) => {
    return $axios({
        url: '/user/list',
        method: 'get',
        params
    })
}

const queryTokenById = (id) => {
    return $axios({
        url: `/token/${id}`,
        method: 'get'
    })
}

// 批量启用禁用
const tokenStatusByBatch = (params) => {
    return $axios({
        url: `/token/status/${params.status}`,
        method: 'post',
        params: { ids: params.ids }
    })
}

// 新增数据接口
const addToken = (params) => {
    return $axios({
        url: '/token',
        method: 'post',
        data: { ...params }
    })
}

// 修改数据接口
const editToken = (params) => {
    return $axios({
        url: '/token',
        method: 'put',
        data: { ...params }
    })
}

//删除token账户
const deleteToken = (ids) => {
    return $axios({
        url: '/token',
        method: 'delete',
        params : { ids: ids }
    })
}

//查看token交易明细
const getTokenLog = params => {
    return $axios({
        url: '/token/log',
        method: 'get',
        params : params
    })
}
