// 查询列表接口
const getCotaskPage = (params) => {
  return $axios({
    url: '/cotasking/getPage',
    method: 'get',
    params
  })
}

const queryCotaskById = (id) => {
  return $axios({
    url: '/cotasking/getById',
    method: 'get',
    params: {id: id}
  })
}