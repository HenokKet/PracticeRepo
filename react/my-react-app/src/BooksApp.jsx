import React, { useState } from "react";

function BooksApp() {
  const [query, setQuery] = useState("");
  const [books, setBooks] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  // Optional: Google Books API works without a key for basic use,
  // but you can add one from Google Cloud if you hit limits.
  const API_KEY = ""; // e.g., "AIzaSyA..."; leave empty if you don't have one

  const fetchBooks = () => {
    const q = query.trim();
    if (!q) {
      setError("Please enter a search term.");
      setBooks(null);
      return;
    }

    setLoading(true);
    setError(null);
    setBooks(null);

    const url =
      `https://www.googleapis.com/books/v1/volumes?q=${encodeURIComponent(q)}&maxResults=10` +
      (API_KEY ? `&key=${API_KEY}` : "");

    fetch(url)
      .then((res) => {
        if (!res.ok) throw new Error("Failed to fetch books.");
        return res.json();
      })
      .then((data) => {
        setBooks(data.items || []);
        setLoading(false);
      })
      .catch((err) => {
        setError(err.message || "Something went wrong.");
        setLoading(false);
      });
  };

  return (
    <div style={{ padding: "20px", maxWidth: "600px", margin: "auto" }}>
      <h2>Google Books Search</h2>
      <input
        type="text"
        placeholder="Search by title, author, or keyword"
        value={query}
        onChange={(e) => setQuery(e.target.value)}
        onKeyDown={(e) => e.key === "Enter" && fetchBooks()}
        style={{ width: "100%", padding: "8px" }}
      />
      <button
        onClick={fetchBooks}
        style={{ marginTop: "10px", padding: "8px", width: "100%" }}
        disabled={loading}
      >
        {loading ? "Loading..." : "Search Books"}
      </button>

      {loading && <p>Loading...</p>}
      {error && <p style={{ color: "red" }}>{error}</p>}

      {books && (
        <div style={{ marginTop: "20px" }}>
          {books.length === 0 ? (
            <p>No results found.</p>
          ) : (
            books.map((b) => {
              const info = b.volumeInfo || {};
              const thumbnail =
                info.imageLinks?.thumbnail || info.imageLinks?.smallThumbnail;
              return (
                <div
                  key={b.id}
                  style={{
                    display: "grid",
                    gridTemplateColumns: "80px 1fr",
                    gap: "12px",
                    padding: "10px 0",
                    borderBottom: "1px solid #eee",
                  }}
                >
                  <div>
                    {thumbnail ? (
                      <img
                        src={thumbnail}
                        alt={info.title || "Book cover"}
                        style={{ width: 80, height: "auto", objectFit: "cover" }}
                      />
                    ) : (
                      <div
                        style={{
                          width: 80,
                          height: 120,
                          background: "#f0f0f0",
                          display: "flex",
                          alignItems: "center",
                          justifyContent: "center",
                          fontSize: 12,
                          color: "#666",
                        }}
                      >
                        No image
                      </div>
                    )}
                  </div>
                  <div>
                    <h3 style={{ margin: "0 0 4px" }}>{info.title || "Untitled"}</h3>
                    <p style={{ margin: "0 0 4px", color: "#555" }}>
                      {info.authors?.join(", ") || "Unknown author"} ·{" "}
                      {info.publishedDate || "n/a"}
                    </p>
                    {info.description && (
                      <p style={{ margin: 0 }}>
                        {info.description.length > 200
                          ? info.description.slice(0, 200) + "…"
                          : info.description}
                      </p>
                    )}
                    {info.previewLink && (
                      <p style={{ margin: "6px 0 0" }}>
                        <a href={info.previewLink} target="_blank" rel="noreferrer">
                          Preview
                        </a>
                      </p>
                    )}
                  </div>
                </div>
              );
            })
          )}
        </div>
      )}
    </div>
  );
}

export default BooksApp;