// 查询列表接口
const getCategoryPage = (params) => {
  return $axios({
    url: '/intelligenceCategory/page',
    method: 'get',
    params
  })
}

// 编辑页面反查详情接口
const queryCategoryById = (id) => {
  return $axios({
    url: `/intelligenceCategory/${id}`,
    method: 'get'
  })
}

// 删除当前列的接口
const deleteCategory = (id) => {
  return $axios({
    url: '/intelligenceCategory',
    method: 'delete',
    params: { id }
  })
}

// 修改接口
const editCategory = (params) => {
  return $axios({
    url: '/intelligenceCategory',
    method: 'put',
    data: { ...params }
  })
}

// 新增接口
const addCategory = (params) => {
  return $axios({
    url: '/intelligenceCategory',
    method: 'post',
    data: { ...params }
  })
}