from pypdf import PdfReader
import sys

files = [
    "bao-cao-bai-tap-lon-mon-phan-tich-va-thiet-ke-phan-mem-phenikaa.pdf",
    "bao-cao-phan-tich-va-thiet-ke-phan-mem-quan-ly-dong-ho-ptich211.pdf",
    "phan-tich-va-thiet-ke-phan-mem-sad-2026.pdf"
]

for file in files:
    try:
        reader = PdfReader(file)
        text = ""
        for page in reader.pages:
            t = page.extract_text()
            if t:
                text += t + "\n"
        with open(file + ".txt", "w", encoding="utf-8") as f:
            f.write(text)
        print("Extracted " + file)
    except Exception as e:
        print("Error reading " + file + ": " + str(e))
