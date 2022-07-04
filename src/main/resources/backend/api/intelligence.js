// 查询列表接口
const getIntelligencePage = (params) => {
  return $axios({
    url: '/intelligence/page',
    method: 'get',
    params
  })
}

// 删除接口
const deleteIntelligence = (ids) => {
  return $axios({
    url: '/intelligence',
    method: 'delete',
    // get会将请求参数拼接在url上
    params: { ids }
  })
}

// 修改接口
const editIntelligence = (params) => {
  return $axios({
    url: '/intelligence',
    method: 'put',
    data: { ...params }
  })
}

// 新增接口
const addIntelligence = (params) => {
  return $axios({
    url: '/intelligence',
    method: 'post',
    // post会将请求参数放在请求体中
    data: { ...params }
  })
}

// 查询详情
const queryIntelligenceById = (id) => {
  return $axios({
    url: `/intelligence/${id}`,
    method: 'get'
  })
}

// 获取情报分类列表
const getCategoryList = (params) => {
  return $axios({
    url: '/category/list',
    method: 'get',
    params
  })
}

// 查情报列表的接口
const queryIntelligenceList = (params) => {
  return $axios({
    url: '/intelligence/list',
    method: 'get',
    params
  })
}

// 文件down预览
const commonDownload = (params) => {
  return $axios({
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
    },
    url: '/common/download',
    method: 'get',
    params
  })
}

// 起售停售---批量起售停售接口
const intelligenceStatusByStatus = (params) => {
  return $axios({
    url: `/intelligence/status/${params.status}`,
    method: 'post',
    params: { ids: params.id }
  })
}