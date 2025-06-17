import useMemberDetails from "~/composables/useMemberDetails.js";


export function useDataFetch(url, options={}){

    const {token} = useMemberDetails();

    options.headers = {
        ...options.headers,
        ...(token.value && {Authorization: `Bearer ${token.value}`})
        ,
    }

    const config = useRuntimeConfig();
    return $fetch(`${config.public.apiBase}${url}`,options);
}