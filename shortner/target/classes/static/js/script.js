document.getElementById("urlForm").addEventListener("submit", async function (e) {
    e.preventDefault();

    const url = document.getElementById("urlInput").value;

    // 🔥 Basic validation
    try {
        new URL(url.startsWith("http") ? url : "https://" + url);
    } catch {
        alert("Invalid URL!");
        return;
    }

    const response = await fetch("/shorten", {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        },
        body: "url=" + encodeURIComponent(url)
    });

    const shortUrl = await response.text();

    const resultDiv = document.getElementById("result");
    const link = document.getElementById("shortLink");
    const clickText = document.getElementById("clickCount");

    // 🔹 Set short link
    link.href = shortUrl;
    link.textContent = shortUrl;

    // 🔹 Extract code
    const code = shortUrl.split("/").pop();

    // 🔹 Fetch click count
    const clickRes = await fetch("/clicks/" + code);
    const clicks = await clickRes.text();

    clickText.textContent = "Clicks: " + clicks;

    resultDiv.style.display = "block";

    // 🔥 Handle redirect manually (since you're using RestController)
    link.onclick = async function (e) {
        e.preventDefault();

        const res = await fetch("/" + code);
        const realUrl = await res.text();

        window.location.href = realUrl;
    };
});