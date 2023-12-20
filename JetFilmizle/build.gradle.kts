version = 1

cloudstream {
    authors     = listOf("keyiflerolsun")
    language    = "tr"
    description = "Binlerce Film İzleme Seçeneğiyle En İyi Film İzleme Sitesi"

    /**
     * Status int as the following:
     * 0: Down
     * 1: Ok
     * 2: Slow
     * 3: Beta only
    **/
    status  = 1 // will be 3 if unspecified
    tvTypes = listOf("Movie")
    iconUrl = "https://jetfilmizle.cx/favicon.ico"
}