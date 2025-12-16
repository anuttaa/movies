async function apiRequest(method, url, body, headers = {}) {
  try {
    const res = await fetch(url, {
      method,
      headers: {
        ...(body ? { 'Content-Type': 'application/json' } : {}),
        ...headers
      },
      body: body ? JSON.stringify(body) : undefined
    });
    const text = await res.text();
    let data;
    try {
      data = text ? JSON.parse(text) : null;
    } catch {
      data = text;
    }
    return { ok: res.ok, status: res.status, data };
  } catch (err) {
    return { ok: false, status: 0, data: { error: err.message } };
  }
}

async function apiGet(url) {
  return apiRequest('GET', url);
}

async function apiPostJson(url, payload) {
  return apiRequest('POST', url, payload);
}

async function apiPutJson(url, payload) {
  return apiRequest('PUT', url, payload);
}

async function apiDelete(url) {
  return apiRequest('DELETE', url);
}

async function apiPutFormData(url, formData) {
  try {
    const res = await fetch(url, {
      method: 'PUT',
      body: formData
    });
    const text = await res.text();
    let data;
    try {
      data = text ? JSON.parse(text) : null;
    } catch {
      data = text;
    }
    return { ok: res.ok, status: res.status, data };
  } catch (err) {
    return { ok: false, status: 0, data: { error: err.message } };
  }
}
